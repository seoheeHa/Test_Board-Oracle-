package com.vam.mapper;

import com.vam.model.Criteria;

public class PageMakerDTO {

	/* 시작 페이지 */
    private int startPage;
    
    /* 끝 페이지 */
    private int endPage;
    
    /* 이전 페이지, 다음 페이지 존재유무 */
    private boolean prev, next;
    
    /*전체 게시물 수*/
    private int total;
    
    /* 현재 페이지, 페이지당 게시물 표시수 정보 */
    private Criteria cri;

	
	/* 생성자 */
	// 현재 페이지에 대한 정보인 Criteria와 게시물 총개수인 total을 파라미터로 부여한 생성자를 만든다.
	// 해당 생성자는 전달받은 파라미터 정보값을 활용하여 계산과정을 거친 후 pageMakerDTO의 변수에 대한
	// 값을 초기화하게 된다.
    public PageMakerDTO(Criteria cri, int total) {
		
        this.cri = cri;
        this.total = total;		
		/* 화면에 보일 마지막 페이지 */
		// 화면에 보일 끝 번호를 구하기위해 Math.ceil(반올림)함수를 사용한다.
		// 현재의 페이지를 10으로 나눈후 그 값을 올림하고 다시 10을 곱한다.
		// 만약 현재페이지가 7이라면, endPage는 Math.ceil(7/10 ) = 1 * 10 = 10
		// 만약 현재페이지가 23이라면, endPage는 Math.ceil(23/10)= 3 * 10 = 30
		// (int)로 다시 형변환하는 이유는 Math.ceil의 반환타입이 double이기 때문이다
        this.endPage = (int)(Math.ceil(cri.getPageNum()/10.0))*10;
		
		
		/* 화면에 보일 시작 페이지 */
		//화면에 표시될 페이지 번호들은 10개이기에 끝번호(endpage)가 구해졌으면 거기에 9를 빼면된다
		// 만약 마지막페이지가 40이면 40-9 = 31이 보여질 시작페이지
		this.startPage = this.endPage -9;

		
		/* 전체 마지막 페이지 */
		// 현재 게시물의 총개수(total)를 통해서 전체페이지의 마지막페이지값을 구한다.
		// 전체페이지(total)을 화면에 표시될 게시물수(amount)로 나눈후 math.ceil로 올림한다.
		// total에 1.0 을 곱해준 이유는 int형인 amount를 나눌 경우 본래는 소수점이 나와야 하는
		// 경우더라도 실제로 소수점을  없애버리고 정수만 리턴하기때문이다.
		// 따라서 1.0값을 total에 곱해줌으로써 double타입으로 형변환을 한후
		// amount값으로 나눈 결과 또한 형변환이 되어 소수점으로 출력이 가능하다.
		// 만약 총게시물(total)수가 235이라면 235 * 1.0 = 235.0 / 10 = 23.5 = 24페이지  
        int realEnd = (int)(Math.ceil(total * 1.0/cri.getAmount()));
		
		
		/* 화면에 보일 마지막 페이지가 유효한지 체크하기 */
		// 전체 마지막페이지(realEnd)가 화면에 보이는 마지막페이지(endPage)보다 작은경우
		// 보이는 페이지(endPage)값조정
		// 화면에 표시될 마지막페이지(endPage)값의 경우는 무조건 10의 단위의 값이 들어있다.
		// 하지만, 실제 전체마지막페이지번호(realEnd)의 값보다 endpage가 더 클경우 문제가 생긴다.
		// 즉, 실제마지막페이지(realEnd)가 7이어야 하는데, 
		// 화면에 표시되는페이지(endPage)가 10의단위라 10이 되어버린다.
		// 이러한 상황을 막기위해 if문을 사용해 endPage가 realPage보다 큰경우는
		// endPage에 realPage값을 저장하도록 한다.
        if(realEnd < this.endPage) {
            this.endPage = realEnd;
        }		
		
		/* 화면에 보일 페이지 이전, 다음 페이지의 존재 여부 */
		
		// 이전페이지(prev)의 존재여부의 경우는 시작페이지(startpage)가 1보다 크면 존재하기에 
		// startpage > 1 이면 true가 되도록 저장한다.
		// 시작페이지(startPage)값이 1보다 큰경우 
        this.prev = this.startPage > 1;
        
		
		
		// 다음페이지(next)의 존재여부는 전체페이지 마지막번호(realEnd)보다 화면에 보이는 마지막번호(endPage)가 작다면
		// 존재하기 때문에 endPage < realEnd인경우 true가 되도록 저장한다.
		// 예를들어 화면에 보이는 마지막페이지(endpage)가 20인데
		// 전체마지막페이지(realend)가 27이라면 -> endpage < realpage 인경우
		// 다음페이지버튼이 존재해야 한다.
		// 반대로 화면에 보이는 마지막페이지(endpage)가 23인데
		// 전체 페이지ㅣ 마지막 번호가 23이면 이동할 수 있는 다음 10개의 페이지가 존재하지 않기에
		// 다음페이지 버튼이 없다.
		
		// 마지막페이지(endPage)값이 1보다 큰경우
        this.next = this.endPage < realEnd;

	
	
	}

    public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	@Override
	public String toString() {
		return "PageMakerDTO [startPage=" + startPage + ", endPage=" + endPage + ", prev=" + prev + ", next=" + next
				+ ", total=" + total + ", cri=" + cri + "]";
	}	
	
	
	
}
package com.vam.mapper;

import java.util.List;

import com.vam.model.BoardVO;
import com.vam.model.Criteria;

public interface BoardMapper {

	/* 게시판 등록 */
    public void enroll(BoardVO board);
 
    
    /* 게시판 목록 */
    // 목록페이지의 경우 대부분 2개 이상의 행에 있는 정보를 반환받아야 한다.
    // 그러므로 List로 반환받는다.
    // List의 가장 큰 장점은 크기가 가변적이라는 점이다.
    public List<BoardVO> getList();

    
    /* 게시판 목록(페이징 처리 적용) */
    // 페이징을 적용한 게시물 목록을 띄우는 쿼리를 실행할 메소드 선언부를 작성한다.
    // 목록페이지와 동일하게 List<BoardVO>반환형타입을 작성한다.
    // pageNum, amount 정보를 DB에 전달하기 위해서 Criteria클래스를 파라미터로 부여한다.
 
    public List<BoardVO> getListPaging(Criteria cri);
    
    
    /* 게시판 조회 */
    // 하나의 게시판 정보를 얻기 위해서는 그 게시판의 게시판 번호를 알아야 하기에
    // 게시판 정보 데이터를 전달받을 수 있도록 int형 변수를 파라미터로 추가한다.
    public BoardVO getPage(int bno);    



    /* 게시판 수정 */
    // 게시판 수정에는 bno, title, content, writer에 대한 데이터를 요하기때문에
    // 해당하는 변수들이 정의되어 있는 BoardVO를 파라미터로 추가한다.
    // 수정을 수행하는 메소드의 경우에는 반환타입이 필요가 없다.
    // 하지만, int형으로 설정할 경우 수정 성공시 1을 반환하고, 수정 실패시 0을 반환한다.
    public int modify(BoardVO board);

    
    /* 게시판 삭제 */
    public int delete(int bno);
    //반환타입은 int형 , 삭제쿼리가 성공하면 1을 반환하고 실패하면 0을 반환한다.

    /* 게시판 총 갯수 */
    public int getTotal(Criteria cri);


}

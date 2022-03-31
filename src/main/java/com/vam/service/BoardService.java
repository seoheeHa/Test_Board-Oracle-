package com.vam.service;

import java.util.List;

import com.vam.model.BoardVO;
import com.vam.model.Criteria;

public interface BoardService {

	 /* 게시판 등록 */
    public void enroll(BoardVO board);
    
    /* 게시판 목록 */
    public List<BoardVO> getList();
    
    /* 게시판 목록(페이징 적용) */
    public List<BoardVO> getListPaging(Criteria cri);
    
    
    /* 게시판 조회 */
    //하나의 게시판 정보를 반환받는 것이 목표이기에
    // 반환타입을 BoardVO로 한다.
    public BoardVO getPage(int bno);

    /* 게시판 수정 */
    //mapper메소드를 호출하기 위한 메소드를 선언한다.
    // 반환타입은 int형으로 한다.
    public int modify(BoardVO board);

    /* 게시판 삭제 */
    // 게시판 번호에 대한 정보만 있으면 되기에, int형을 변수를 파라미터로 부여한다.
    public int delete(int bno);

    
    /* 게시판 총 갯수 */
    public int getTotal(Criteria cri);
}

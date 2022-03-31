package com.vam.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vam.mapper.PageMakerDTO;
import com.vam.model.BoardVO;
import com.vam.model.Criteria;
import com.vam.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	
	@Autowired
	private BoardService bservice;
	
	/*
	 * // 게시판 목록페이지
	 * 
	 * @GetMapping("/list")// == @RequestMapping(value="",method=Request.GET) public
	 * void boardListGET(Model model) { log.info("게시판 목록 페이지 진입");
	 * model.addAttribute("list", bservice.getList()); }
	 */
	
	
	/* 게시판 목록 페이지 접속(페이징 적용) */
    @GetMapping("/list")
    public void boardListGET(Model model, Criteria cri) {
        
        log.info("boardListGET");
        
        model.addAttribute("list", bservice.getListPaging(cri));
        
        int total = bservice.getTotal(cri);
        
        PageMakerDTO pageMake = new PageMakerDTO(cri, total);
        
        model.addAttribute("pageMaker", pageMake);
        
    }    	
	
	// 게시판 등록페이지
	@GetMapping("/enroll")
	public void boardEnrollGET() {
		log.info("게시판 등록 페이지 진입");
	}
	
	/* 게시판 등록하기 */
    @PostMapping("/enroll")
    public String boardEnrollPOST(BoardVO board, RedirectAttributes rttr) {
        
        log.info("BoardVO : " + board);
        
        bservice.enroll(board);
        
        rttr.addFlashAttribute("result", "enrol success");
        
        return "redirect:/board/list";
    }

    
    /* 게시판 상세조회 */
    // view로부터 '게시판 번호'를 전달 받기위해 int형 변수를 파라미터로 추가한다.
    // 게시판조회페이지에서 쿼리를 실행 후 전달받는 BoardVO객체 데이터를 전달하기위해
    // Model파라미터도 추가한다.
    // view에서 전송하는 pageNum, amount데이터를 사용할수있도록 Criteria 클래스를 파라미터로 부여함
    @GetMapping("/get")
    public void boardGetPageGET(int bno, Model model, Criteria cri) {
    	
    	//addAttribute 메소드를 호출하여 "pageInfo" 속성명에 
    	// BoardService 인터페이스의 getPage()메소드 호출하여 반환받은
    	// BoardVO객체를 속성 값으로 저장한다.
    	model.addAttribute("pageInfo", bservice.getPage(bno));
    
    	// addAttribute메소드를 호출하여 cri속성명에 속성값으로 view로부터
    	// 전달받은 Criteria 인스턴스를 저장한다.ㄴ
    	model.addAttribute("cri", cri);
    }
    
    
    /* 게시판 수정페이지 이동 = get */
    // 조회페이지(get.jsp) ----> 수정페이지(modify.jsp)이동해주는 메서드
    // 조회페이지 이동메서드와 동일하다.
    // int형 파라미터와 해당게시판 내용을 호출하는 service메서드를 호출한다.
    @GetMapping("/modify")
    public void boardModifyGET(int bno, Model model, Criteria cri) {
    	model.addAttribute("pageInfo", bservice.getPage(bno));
    	model.addAttribute("cri", cri);
    }
    
    
    /* 게시판 수정  = post */
    // 수정페이지(modify.jsp)에서 내용변경후 수정완료 눌렀을때 실행되는 메서드
    @PostMapping("/modify")
    public String boardModifyPOST(BoardVO board, RedirectAttributes rttr) {
    	// 수정할 데이터를 가져오기 위해 BoardVO클래스를 파라미터로 부여하였고,
    	// 수정을 수행한 이후에 redirect 방식으로 list페이지 이동시 데이터를 함께
    	// 전송하기 위해 RedirectAttribute객체를 파라미터로 부여한다
    	bservice.modify(board);
    	rttr.addFlashAttribute("result", "modify success");
    	// 리스트페이지 이동시 수정이 완료되었음을 알리는 경고창을 띄우기 위해
    	// "modify success"스트링 데이터를 result속성값에 저장하는 addFlashAttribute메소드를 호출한다
    	
    	return "redirect:/board/list";
    	// return타입은 String타입으로 작성하고,
    	// redirect방식으로 list페이지로 이동하도록 한다.
    }
    
    
    /* 게시판 삭제 */
    @PostMapping("/delete")
    public String boardDeletePOST(int bno, RedirectAttributes rttr) {
    	// 삭제쿼리를 실행하기 위해 게시판 번호를 요하기에 int형변수를 파라미터로 부여함
    	// 삭제 기능을 수행하고 나서 redirect방식으로 리스트페이지 이동시 데이터를 함께
    	// 전송하기 위해 RedirectAttributes 객체를 파라미터로 부여함 
    	bservice.delete(bno);
    	//서비스에서 delete 메서드를 불러와 해당하는 bno를 없애고 
    	rttr.addFlashAttribute("result", "delete success");
    	//리스트페이지에서 삭제가 완료되었음을 경고창을 띄우기 위해서
    	// result속성값에 delete success스트링 데이터를 저장하는 addFlashAttribute메소드를 호출함
    	return "redirect:/board/list";
    	// return타입은 string타입으로 redirect방식으로 리스트페이지로 이동함
    }
}

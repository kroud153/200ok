package board.board.controller;

import java.util.List;

import board.NaverAPI.ApiExamSearchBlog;
import board.board.dto.BoardDto;
import board.board.service.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	private ApiExamSearchBlog Shop;

	@RequestMapping("/board/openBoardList.do")
	public ModelAndView openBoardList() throws Exception{

		ModelAndView mv = new ModelAndView("/board/boardList");

		List<BoardDto> list = boardService.selectBoardList();
		mv.addObject("list", list);
		return mv; 
	}

	@RequestMapping("/board/openBoardWrite.do")
	public String openBoardWrite() throws Exception{

		return "/board/BoardWrite";
	}

	@RequestMapping("/board/insertBoard.do")
	public String insetBoard(BoardDto board) throws Exception{
		boardService.insertBoard(board);
		return "redirect:/board/openBoardList.do";
	}

	@RequestMapping("/board/openBoardDetail.do")
	public ModelAndView openBoardDetail(@RequestParam int boardIdx) throws Exception{
		ModelAndView mv = new ModelAndView("/board/boardDetail");

		BoardDto board = boardService.selectBoardDetail(boardIdx);
		mv.addObject("board", board);
		return mv;
	}

	@RequestMapping("/board/updateBoard.do")
	public String updateBoard(BoardDto board) throws Exception{
		boardService.updateBoard(board);
		return "redirect:/board/openBoardList.do";
	}

	@RequestMapping("/board/deleteBoard.do")
	public String deleteBoard(int boardIdx) throws Exception{
		boardService.deleteBoard(boardIdx);
		return "redirect:/board/openBoardList.do";
	}

	@RequestMapping("/board/boardShop.do")
	@ResponseBody
	public ModelAndView boardShop(@RequestParam(required=false)String keywords) {
		ModelAndView mav = new ModelAndView("/board/boardShop");
		//List<board.board.shop.Shop> shopList = null;
		if(keywords != null)
		{
			mav.addObject("ShopList",Shop.shopSearch(keywords));
		}
		mav.setViewName(keywords);
		return mav;
	}

//	@RequestMapping("/board/boardShopList.do")
//	@ResponseBody
//	public ModelAndView ShopList(@RequestParam(required=false)String keywords) {
//		ModelAndView mav = new ModelAndView("/board/boardShopList");
//		//List<board.board.shop.Shop> shopList = null;
//		if(keywords != null)
//		{
//			mav.addObject("ShopList",Shop.shopSearch(keywords));
//		}
//		mav.setViewName(keywords);
//		return mav;
//	}
}
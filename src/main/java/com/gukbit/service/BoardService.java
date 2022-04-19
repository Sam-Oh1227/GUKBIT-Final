package com.gukbit.service;


import com.gukbit.domain.Board;
import com.gukbit.domain.User;
import com.gukbit.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Page<Board> findBoardList(Pageable pageable) {
        Sort sort = Sort.by("bid").descending();
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, 5,sort);
        return boardRepository.findAll(pageable);
    }
    public Page<Board> findBoardSampleNew(Pageable pageable) {
        Sort sort = Sort.by("date").descending();
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, 10,sort);
        return boardRepository.findAll(pageable);
    }

    public Page<Board> findBoardSampleBest(Pageable pageable) {
        Sort sort = Sort.by("view").descending();
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, 10,sort);
        return boardRepository.findAll(pageable);
    }

    public void board_Create(Board board) {
        boardRepository.save(board);
    }


    public Board findBoardByIdx(Integer bid) {
        return boardRepository.findById(bid).orElse(new Board());
    }

    public void deleteBoard(Integer bid){
        boardRepository.deleteById(bid);
    }

    public void updateBoard(Board board){
        boardRepository.save(board);
    }

    public boolean writeUserCheck(User loginUser, Integer bid){
        if(boardRepository.findById(bid).isEmpty()){
            return false;
        }
        if(loginUser == null){
            return false;
        }
        Board board = boardRepository.findById(bid).get();
        if(board.getAuthor().equals(loginUser.getUserId())){
            return true;
        }
        return false;
    }

    public void plusView(){

    }
}

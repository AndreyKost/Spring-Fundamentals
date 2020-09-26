package wpartone.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import wpartone.model.entity.Comment;
import wpartone.model.service.CommentServiceModel;
import wpartone.repository.CommentRepository;
import wpartone.service.CommentService;

import java.util.HashMap;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void add(CommentServiceModel commentServiceModel) {
        this.commentRepository.saveAndFlush(this.modelMapper.map(commentServiceModel, Comment.class));
    }

    @Override
    public double getAvgScore() {
        return this.commentRepository.findAll()
                .stream()
                .mapToDouble(Comment::getScore)
                .average()
                .orElse(0D);
    }

    @Override
    public HashMap<Integer, Integer> getScoreMap() {
        HashMap<Integer,Integer> scoreMap=new HashMap<>();
        this.commentRepository.findAll()
                .forEach(comment -> {
                    int score=comment.getScore();
                    if(scoreMap.containsKey(score)){
                        scoreMap.put(score,scoreMap.get(score)+1);
                    }
                    scoreMap.put(score,1);
                });

        return scoreMap;

    }
}

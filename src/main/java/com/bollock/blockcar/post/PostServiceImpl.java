package com.bollock.blockcar.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements IPostService {
	@Autowired
	private PostRepository postRepository;

	@Override
	public Post insertPost(Post post) {
		return postRepository.save(post);
	}

	@Override
	public List<Post> findPostsAll() {
		return postRepository.findAll();
	}

	@Override
	public Post findPost(Long no) {
		return postRepository.findById(no).get();
	}

	@Override
	public Post updatePost(Post post) {
		postRepository.flush();
		return post;
	}

	@Override
	public List<Post> findUserPosts(Long no) {
		return postRepository.findByUserNo(no);
	}

}

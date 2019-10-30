package com.bollock.blockcar.post;

import java.util.List;

public interface IPostService {

	Post insertPost(Post post);

	List<Post> findPostsAll();

	Post findPost(Long no);

	Post updatePost(Post post);

	List<Post> findUserPosts(Long no);

}

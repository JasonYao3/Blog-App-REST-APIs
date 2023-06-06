package com.springboot.blog.controller;

import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(
        name = "CRUD REST APIs for Comment Resource"
)
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(
            summary = "Create Comment REST API",
            description = "Create Comment REST API is used to create comment for a post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") Long postId,
                                                    @Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get Comments By Post Id REST API",
            description = "Get comments by post Id REST API is used to fetch all comments by post id from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId") Long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @Operation(
            summary = "Get Comment By Post Id REST API",
            description = "Get a comment by post Id REST API is used to fetch a comment by post id from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") Long postId,
                                                     @PathVariable(value = "commentId") Long commentId) {
        CommentDto commentDto = commentService.getCommentsById(postId, commentId);

        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    @Operation(
            summary = "Update Comment By Post Id REST API",
            description = "Update a comment by post Id REST API is used to update a comment by post id from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") Long postId,
                                                    @PathVariable(value = "commentId") Long commentId,
                                                    @Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.updateComment(postId, commentId, commentDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Delete Comment By Post Id REST API",
            description = "Delete a comment by post Id REST API is used to delete a comment by post id from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "postId") Long postId,
                                                @PathVariable(value = "commentId") Long commentId) {
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }

}

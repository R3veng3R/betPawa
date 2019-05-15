package com.aj.pawatask.utils.dto.mappers;

import com.aj.pawatask.models.Comment;
import com.aj.pawatask.utils.dto.CommentDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CommentMapper {
    Comment commentDTOtoComment(CommentDTO dto);
}

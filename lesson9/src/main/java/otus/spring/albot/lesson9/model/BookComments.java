package otus.spring.albot.lesson9.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import otus.spring.albot.lesson9.entity.Comment;

import java.util.List;

/**
 * <pre>
 * $Id: $
 * $LastChangedBy: $
 * $LastChangedRevision: $
 * $LastChangedDate: $
 * </pre>
 *
 * @author Dmitrii Albot
 */
@Getter
@AllArgsConstructor
public class BookComments {
    private String book;
    private List<Comment> comments;

    @Override
    public String toString() {
        return "The book \"" + book + "\" has the next list of comments: " + formatComments();
    }

    private String formatComments() {
        if (!comments.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            formatComment(sb, comments.get(0));
            for (int i = 1; i < comments.size(); i++) {
                sb.append(", ");
                formatComment(sb, comments.get(i));
            }
            sb.append("]");
            return sb.toString();
        }
        return "[**no comments**]";
    }

    private void formatComment(StringBuilder stringBuilder, Comment comment) {
        stringBuilder.append(String.format("\"(id=%d) %s\"", comment.getId(), comment.getComment()));
    }
}

package stream.lexlab.simplecms.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PageNotFound extends RuntimeException {
    public PageNotFound(String msg) { super(msg); }
}

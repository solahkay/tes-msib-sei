package solahkay.msib.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebResponse<T> {

    private String message;

    private T data;

    private String errors;

    private PagingResponse paging;

}

package domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
@Setter
@ToString
public class Criteria {

    private int pageNum;
    private int amount;
    private int rowNum;
    private int rn;

    private String type;

    private String keyword;

    public Criteria() {
        this(1, 10);
    }

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
        this.rowNum = pageNum * amount;
        this.rn = (pageNum - 1) * amount;
    }

    public String[] getTypeArr() {
        return type == null ? new String[]{} : type.split("");
    }

    public String getListLink() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
                .queryParam("pageNum", this.getPageNum())
                .queryParam("amount", this.getAmount())
                .queryParam("type", this.getType())
                .queryParam("keyword", this.getKeyword());

        return builder.toUriString();
    }
}

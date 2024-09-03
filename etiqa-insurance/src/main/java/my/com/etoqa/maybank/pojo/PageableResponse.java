package my.com.etoqa.maybank.pojo;

public record PageableResponse(int page, int per_page, int total, int total_pages, Object data, Integer resultCode, String message) {}

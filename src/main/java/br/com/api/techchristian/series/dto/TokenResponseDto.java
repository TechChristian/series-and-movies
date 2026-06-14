package br.com.api.techchristian.series.dto;

public record TokenResponseDto(
        String token,
        Long expirationTime
) {
}

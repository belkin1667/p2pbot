package com.l2lhackathon.peers.controller.excpetion;

import java.time.Instant;

public record ErrorResponseDto(Instant timestamp, Integer status, String message, String stackTrace) { }

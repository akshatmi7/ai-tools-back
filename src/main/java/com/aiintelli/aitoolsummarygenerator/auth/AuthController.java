package com.aiintelli.aitoolsummarygenerator.auth;

import com.aiintelli.aitoolsummarygenerator.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> req) {
        try {
            User user = authService.register(req.get("name"), req.get("email"), req.get("password"));
            String token = authService.login(req.get("email"), req.get("password"));
            return ResponseEntity.ok(Map.of("token", token, "user", user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> req) {
        try {
            String token = authService.login(req.get("email"), req.get("password"));
            User user = authService.processGoogleUser("", req.get("email")); // For local login, just fetch user
            return ResponseEntity.ok(Map.of("token", token, "user", user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Google OAuth endpoints (simplified for frontend-initiated OAuth)
    @GetMapping("/google")
    public RedirectView googleAuth() {
        String googleOAuthUrl = "https://accounts.google.com/o/oauth2/v2/auth?client_id=YOUR_GOOGLE_CLIENT_ID&redirect_uri=YOUR_REDIRECT_URI&response_type=code&scope=openid%20email%20profile";
        return new RedirectView(googleOAuthUrl);
    }

    @GetMapping("/google/callback")
    public ResponseEntity<?> googleCallback(@RequestParam("code") String code) {
        // Exchange code for token, get user info, register/login user, return JWT
        // This requires Google client secret, token exchange, and userinfo fetch
        return ResponseEntity.status(501).body(Map.of("error", "Google OAuth not fully implemented in this stub."));
    }
} 
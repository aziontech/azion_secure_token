# Architecture — azion_secure_token

## Overview

Reference implementations for generating Azion Secure Token authentication URLs. Provides Python and PHP scripts that create HMAC-MD5 signed, time-limited URLs for content protection on the Azion Edge Platform. Commonly used to secure video assets (HLS, progressive download), authentication headers, and cookie signatures.

## Token Generation

```
Input: secret + URI + expiration timestamp
    │
    ├── MD5 hash (secret + uri + expire)
    ├── Base64 encode the digest
    └── URL-safe transform (remove =, replace +/ with -_)
    │
    ▼
Output: http://example.org/my/uri?st=<token>&e=<expire>
```

## Project Structure

```
azion_secure_token/
├── secure_token.py         Python token generator
├── secure_token.php        PHP token generator
├── README.md               Usage guide and platform validation docs
├── CONTRIBUTING.md          Contribution guidelines
├── CLA.md                  Contributor License Agreement
├── CODE_OF_CONDUCT.md      Code of conduct
└── LICENSE                 MIT License
```

## Token Parameters

| Parameter | Description |
|-----------|-------------|
| `secret` | Shared secret string (configured in edge function Args) |
| `uri` | Protected resource path |
| `expire` | Unix timestamp for token expiration |

## Platform Validation

When a request reaches Azion's edge, the platform:
1. Checks if current time exceeds the `e=` expiration parameter → returns `410 Gone`
2. Recomputes the MD5 signature and compares with `st=` parameter → returns `403 Forbidden` on mismatch
3. Forwards request to origin on valid, unexpired token

The edge function receives the secret via Args JSON: `{"secure_token_secret": "..."}`.

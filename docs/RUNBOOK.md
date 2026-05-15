# Runbook — azion_secure_token

## Service Identity

| Field | Value |
|-------|-------|
| Name | azion_secure_token |
| Type | Reference implementation / code samples |
| Languages | Python 2, PHP |
| Purpose | Generate time-limited signed URLs for Azion Secure Token |

## Usage

### Python

```bash
# Edit secret, uri, and expire in secure_token.py
python secure_token.py
# Output: http://www.example.org/my/uri?st=<token>&e=<expire>
```

### PHP

```bash
# Edit secret, uri, and expire in secure_token.php
php secure_token.php
# Output: http://www.example.org/my/uri?st=<token>&e=<expire>
```

## Integration with Azion Platform

1. Generate token using either script (or your own implementation)
2. Configure edge function with the same secret in Args: `{"secure_token_secret": "your_secret"}`
3. Client appends `?st=<token>&e=<expire>` to protected URLs
4. Edge validates signature and expiration on each request

See the [Secure Token guide](https://www.azion.com/en/documentation/products/guides/secure-token/) for setup instructions.

## Common Issues

### 1. 403 Forbidden on Valid Token

**Cause**: Secret mismatch between token generator and edge function Args.

**Resolution**: Verify `secure_token_secret` in the edge function configuration matches the secret used to generate the token.

### 2. 410 Gone

**Cause**: Token has expired (current time > `e=` parameter).

**Resolution**: Generate a new token with a future expiration timestamp.

### 3. Python 2 Compatibility

**Note**: The Python script uses Python 2 syntax (`print` statement). For Python 3, use `print()` function and encode strings to bytes before hashing.

## Escalation

| Level | Contact | When |
|-------|---------|------|
| L1 | Team Dev Tools & Integrations | Script issues, new language implementations |
| L2 | Product / Edge Functions | Platform-side token validation issues |

# Azion Secure Token Authentication

Azion **Secure Token** allows you to create token-based, time-limited URLs. This way, you can give a particular user access to a link for a specific amount of time.

The creation and validation of signatures for cookies, authentication headers, and other security measures can be done using these tokens, which can be altered in a variety of ways. Utilizing both HLS and Progressive Download, the solution is frequently used to secure video assets, including those used for live streaming and on-demand content.

> Read more on the [How to install the Secure Token integration](https://www.azion.com/en/documentation/products/guides/secure-token/) guide.

---

## Generating the token

In this repository, you’ll find two example scripts to generate the tokens, a Python and a PHP script. You can run them locally and generate the token or you can generate these tokens on your own platform with your own code.

Using the Python script as an example, you’ll have the following source code:

```
#!/usr/bin/env python

import base64
import hashlib

secret = 'mysecret'
uri = '/my/uri'
expire = '1470055000'

md5 = hashlib.md5()
md5.update(secret + uri + expire)
token = base64.b64encode(md5.digest()).replace('=','').replace('+','-').replace('/','_')

print 'http://www.example.org%s?st=%s&e=%s' % (uri, token, expire)
```

Where:

- `secret`: a string of your choice that will be used to generate the token.
- `uri`: the URI to use with the token.
- `expire`: the expiration time of the token.

Save the generated token, regardless of the way you generated it.

In this case, the edge function expects to find a token in the `st= GET` parameter and an expiration time in the`e=` parameter. Tokens are query strings in the format `?st=XXX&e=YYY. For example: `?st=m6WCATfRgS_5lcyChCPgrw&e=1470055000`.

The full request URL would look like this:

`http://www.example.org/my/uri?st=m6WCATfRgS_5lcyChCPgrw&e=1470055000`

---

## Adding the token via Real-Time Manager

To start using **Secure Token**, you need to get and instantiate the integration's function as explained in the [how-to guide](https://www.azion.com/en/documentation/products/guides/secure-token/). While [setting up the integration](https://www.azion.com/en/documentation/products/guides/secure-token/#setting-up-the-integration), you'll need to add the token in the **Args** tab: 

```
{
    "secure_token_secret": "thatisthesecret"
}
```

​​Where `secure_token_secret` will be the secret string you’ve passed on the code when generating the token in the previous step.

---

## Azion's platform validation

Azion's platform will check for two conditions:

Is the current time greater than the expiration time specified in the token?
Does Azion's signature match the signature of the token?

If the signature is invalid, the system will return a `403` error. If the signature is valid but the expiration time has elapsed, the system will return a `410` error. The different response codes help to debug (and are also "more correct"). A malicious user can't modify the expiration time of their token (if they did, the signature would no longer match).

---

## Client Side Scripts

The client or web application must be able to generate tokens to authenticate with Azion's platform.

You can find pseudo-code examples here on GitHub. Feel free to modify them and, in case you write your own codes, share them with Azion's team.


### Azion Secure Token Authentication

Azion Secure Token give you the ability to create URLs that expire. If you want to give a particular user access to a link for a specific amount of time, you'll need those tokens. You can create tokens with many different variations and use it to create and validade signatures in cookies, authentication headers and others, and they are commonly used to secure video assets, including HLS and Progressive Download, no matter if the content is a live streaming or on-demand.


### Real Time Manager

To start using Secure Tokens, go to your Cloud Security > Edge Firewall configuration, define a secret and enable it on your Content Delivery > Rules Engine settings. Documentation is available here: https://www.azion.com.br/developers/documentacao/ 

NOTE: Please generate your own key before using this code. The example key will intentionally cause an error if you use it. Please generate a new key with openssl rand -base64 32.

This code expects to find a token in the `st=` GET parameter and a expire in the `e=` parameter. Tokens take the format of `?st=XXX&e=YYY` and look like this: `?st=m6WCATfRgS_5lcyChCPgrw&e=1470055000`. The full request URL would look like this:

Example: `http://www.example.org/my/uri?st=m6WCATfRgS_5lcyChCPgrw&e=1470055000`


### Azion Edge Servers

Azion Nginx running at the Edge Servers will check for two things:

Is the current time greater than the expiration time specified in the token?
Does our signature match the signature of the token?

If the signature is invalid, Nginx will return a 403. If the signature is valid but the expiration time has elapsed, Nginx will return a 410. The different response codes are helpful for debugging (and also "more correct"). It is not possible for a malicious user to modify the expiration time of their token (if they did the signature would no longer match).


### Client Side Scripts

The client or web application will need to be able to generate tokens to authenticate with Azion Nginx.

You can find pseudo code examples here on github. Feel free to modify them and in case you write your own codes, share them with us :)


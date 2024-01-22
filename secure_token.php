<?php

$secret = "mysecret";
$uri = "/my/uri";
$expire = "1470055000";

$token = str_replace(["=", '+', '/'], ['', '-', '_'], base64_encode(md5($secret . $uri . $expire, true)));

echo "http://www.example.org" . $uri . "?st=" . $token . "&e=" . $expire . "\n";

?>

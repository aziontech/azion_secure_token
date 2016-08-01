<?php

$secret = "mysecret";
$uri = "/my/uri";
$expire = "1470055000";

$token = base64_encode(md5($secret . $uri . $expire, true));
$token = str_replace("=", "", $token);
$token = strtr($token, "+/", "-_");

print "http://www.example.org" . $uri . "?st=" . $token . "&e=" . $expire . "\n";

?>

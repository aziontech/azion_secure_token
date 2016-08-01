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

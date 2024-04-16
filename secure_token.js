const crypto = require('crypto');

const secret = 'mysecret';
const uri = '/my/uri';
const expire = '1470055000';

const md5 = crypto.createHash('md5');
md5.update((secret + uri + expire), 'utf-8');
const token = Buffer.from(md5.digest()).toString('base64').replace(/=|\+|\//g, (match) => ({
  '=': '',
  '+': '-',
  '/': '_',
}[match]));

console.log(`http://www.example.org${uri}?st=${token}&e=${expire}`);


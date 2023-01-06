
const data = `CI1=${cardNumber}|CI2=${cvv}|CI3=${nameOnCard}|CI4=${expiryYear}|CI5=${expiryMonth}`
const encrypted64 = getEncryptedData(publicKey, data, version); // encode a string


const getEncryptedData = (key, data, version) => {
    const pem = convertHexToPEM(key);
    const pemFormat = `-----BEGIN PUBLIC KEY-----\n${pem}\n-----END PUBLIC KEY-----`;
    const publicKeyForForge = forge.pki.publicKeyFromPem(pemFormat);
    const encyptedtext = btoa(publicKeyForForge.encrypt(data, 'RSAES-PKCS1-V1_5'))
    return `${encyptedtext}___${version}`;
}

function returnMyVariable(){
return cardNumber;
}

function convertHexToPEM(hexPublicKey) {
let buffer = '';
let hex = 0;
let output = '';
for (var i=0; i<hexPublicKey.length; i++)
{
if (isHex(hexPublicKey.charAt(i))) { buffer += hexPublicKey.charAt(i); }

if (buffer.length == 2)
{
hex = parseInt(buffer,16);
output += String.fromCharCode(hex);
buffer = "";
}
}
output = btoa(output).split(/(.{75})/).join("\n").replace(/\n+/g,"\n").trim();
return output;
}

function isHex(publicKey)
{
  if (publicKey.length == 0) { return false; }

  for (var i=0; i<publicKey.length; i++)
  {
    if ( !( (publicKey.charAt(0) >= "0" && publicKey.charAt(0) <= "9") || (publicKey.charAt(0) >= "a" && publicKey.charAt(0) <= "f") || (publicKey.charAt(0) >= "A" && publicKey.charAt(0) <= "F") ) ) { return false; }
  }
  return true;
}

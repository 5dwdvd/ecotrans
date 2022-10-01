import qrcode
from datetime import datetime
import hashlib
from PIL import Image
 
current_30_timestamp = f"{datetime.now().timestamp()//30:.0f}"
hashed_string = hashlib.sha256(current_30_timestamp.encode('utf-8')).hexdigest()

qr = qrcode.QRCode(
    version=7,
    error_correction=qrcode.constants.ERROR_CORRECT_L,
    box_size=100,
    border=4,
)
qr.add_data(hashed_string)
qr.make(fit=True)
img = qr.make_image(fill_color=(89, 173, 148), back_color=(255, 254, 249))

logo_file = 'ecotrans.png'
 
logo = Image.open(logo_file)
 
# taking base width
basewidth = int(img.size[1] / 5)
 
# adjust image size
wpercent = (basewidth/float(logo.size[0]))
hsize = int((float(logo.size[1])*float(wpercent)))
logo = logo.resize((basewidth, hsize), Image.ANTIALIAS)
 
# set size of QR code
pos = ((img.size[0] - logo.size[0]) // 2,
       (img.size[1] - logo.size[1]) // 2)
img.paste(logo, pos)
 
# save the QR code generated
img.save('qr.png')



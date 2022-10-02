import qrcode
from datetime import datetime
import hashlib

current_30_timestamp = f"{datetime.now().timestamp()//30:.0f}"
hashed_string = hashlib.sha256(current_30_timestamp\
    .encode('utf-8')).hexdigest()

def qr_now(name,location):
    qr = qrcode.QRCode(
        version=7,
        error_correction=qrcode.constants.ERROR_CORRECT_L,
        box_size=100,
        border=4,
    )
    qr.add_data(f"{location}-{hashed_string}")
    qr.make(fit=True)
    img = qr.make_image(fill_color=(89, 173, 148),
                        back_color=(255, 254, 249))
    img.save(name)


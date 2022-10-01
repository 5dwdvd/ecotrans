# Environment

All you really need is the `qrcode` library. Using conda, could also do the following to reproduce the environment:
```
conda env create -f qr_backend.yml
conda activate qr-backend
poetry install
```

# How it works
The following generates a QR code valid within a 30-second timeframe.
```python
from qr_backend.generate import qr_now
qr_now('now.png')
```
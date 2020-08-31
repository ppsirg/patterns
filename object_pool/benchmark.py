"""
check if meta contain: 
- load time
"""

from utils import measure_time
from pool_pattern import reusableBrowser, Pool


@measure_time
def regular_audit(pages):
    for page in pages:
        # create browser
        browser = reusableBrowser()
        # do audit
        browser.check_url(page)


@measure_time
def pool_based_audit(pages):
    # create browser pool
    pool = Pool(1)
    for page in pages:
        # do audit
        browser.check_url(page)


if __name__ == "__main__":
    audited_pages =  [
        'www.google.com', 'www.buuson.com',
        'www.whatsapp.com', 'www.facebook.com',
    ]
    result = regular_audit(audited_pages)
    result = pool_based_audit(audited_pages)

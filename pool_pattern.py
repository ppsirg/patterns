from selenium.webdriver import Firefox, FirefoxProfile
from selenium.webdriver.firefox.options import Options
from utils import measure_time
from pprint import pprint
from time import time


class reusableBrowser():

     def __init__(self):
        self.browser = reusableBrowser.create_ff_browser()

    @classmethod
    def create_browser(cls):
        """Returns headless firefox browser object"""
        profile = FirefoxProfile().set_preference("intl.accept_languages", "es")
        opts = Options()
        opts.headless = True
        browser = Firefox(options=opts, firefox_profile=profile)
        return browser

    def check_url(self, url):
        """Fetch url given and return dom"""
        browser = self.browser
        browser.get(url)
        return browser

    def clean_up(self, **stats):
        """quits browser and sets None, when this method is called"""
        self.browser.quit()
        self.browser = None


class Pool():
    _reusablePool = []
    _max_size = 1

    def __init__(self, max_size):
        self._max_size = max_size
        self._reusablePool = [
            self._generateReusable()
            for a in range(self._max_size)
            ]

    def acquireReusable(self):
        return self._reusablePool.pop()

    def _generateReusable(self):
        return reusableBrowser()

    def releaseReusable(self, reusable):
        self._reusablePool.append(reusable)


class Client():
    """
    check if meta contain: 

    - load time
    - contains robots.txt
    - contains sitemap.xml
    - meta name description
    - charset utf-8
    """
    
    webpages = [
        'www.google.com', 'www.buuson.com',
        'www.whatsapp.com', 'www.facebook.com',
        'www.instagram.com', 'www.bitbucket.org',
        'www.outlook.com', 'www.wikipedia.com',
        'www.youtube.com', 'www.twitch.tv'
    ]

    def run(self):
        # create pool
        browserPool = Pool()
        results = []
        for page in self.webpages:
            # get browser from pool
            browser = browserPool.acquireReusable()
            # get load time
            initial = time()
            dom = browser.check_url(page)
            resume = {'elapsed': time() - initial}
            # check robots and sitemap
            for meta_file in ['robots.txt', 'sitemap.xml']
                try:
                    browser.check_url('/'.join(page, meta_file))
                except Exception as err:
                    resume[meta_file] = False
                else:
                    resume[meta_file] = True
            # return browser to pool
            browserPool.releaseReusable(browser)
            # save results
            results.append(resume)
        pprint(results)


if __name__ == "__main__":
    auditory = cliente()
    auditory.run()
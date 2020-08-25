from selenium.webdriver import Firefox
from selenium.webdriver.firefox.options import Options


class reusableBrowser():

    def __init__(self):
        self.browser = reusableBrowser.create_browser()

    @classmethod
    def create_browser(cls):
        """Returns firefox browser object"""
        browser = Firefox()
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

from pprint import pprint
from time import time
from reusable import reusableBrowser
from simple_pool import Pool


class Client():
    """
    check if meta contain: 

    - load time
    - contains robots.txt
    - contains sitemap.xml
    - meta name description
    """
    
    webpages = [
        'www.google.com', 'www.buuson.com',
        'www.yatestogo.com', 'www.facebook.com',
        'www.instagram.com', 'www.bitbucket.org',
        'www.outlook.com', 'www.wikipedia.com',
        'www.youtube.com', 'www.twitch.tv'
    ]

    def run_analysis(self, browserPool):
        results = []
        for page in self.webpages:
            print(f'Trabajando en {page}')
            # get browser from pool
            browser = browserPool.acquireReusable()
            # get load time
            initial = time()
            base_url = f'https://{page}'
            dom = browser.check_url(base_url)
            resume = {'name': page, 'elapsed': time() - initial}
            print(resume)
            # check robots and sitemap
            for meta_file in ['robots.txt', 'sitemap.xml']:
                try:
                    browser.check_url('/'.join(base_url, meta_file))
                except Exception as err:
                    resume[meta_file] = False
                else:
                    resume[meta_file] = True
            # return browser to pool
            browserPool.releaseReusable(browser)
            print(f'Terminando con {page}')
            # save results
            results.append(resume)
        pprint(results)


class Auditory():

    def audit(self):
        browserPool = Pool(1)
        auditory = Client()
        auditory.run_analysis(browserPool)
        browserPool.close()


if __name__ == "__main__":
    test = Auditory()
    test.audit()
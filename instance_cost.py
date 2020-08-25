from selenium import webdriver
from time import time
from utils import measure_time


class minimal():
    pass


class mindriver():
    def __init__(self):
        self.driver = webdriver.Firefox()

    def __enter__(self):
        return self

    def __exit__(self, *args, **kwargs  ):
        self.close()

    def close(self):
        self.driver.close()
        self.driver = None


@measure_time
def run_check():
    with mindriver() as lol:
        pass


@measure_time
def run_mini():
    minimal()


if __name__ == "__main__":
    run_check()
    run_mini()
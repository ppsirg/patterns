from utils import measure_time
from random import randint


class smallObject():
    
    def __init__(self):
        pass

    def validate(self, number):
        return True


class bigObject():
    
    def __init__(self):
        self.vector = [a*randint(0,23) for a in range(100000)]
        self.squares = [a*a*a for a in range(100000)]
        self.countries = {
            'colombia': (1,200),
            'alemania': (345,569),
            'peru': (100,1234),
            'ecuador': (123, 4554),
            'argentina': (456, 948),
            'polonia': (1000, 3000),
            'chile': (67, 123),
        }
        self.compound_data = {
            k: sum(self.squares[v[0]: v[1]]) * sum(self.vector[v[0]:v[1]])
            for k, v in self.countries.items()
            }

    def validate(self, number):
        return number in self.vector and number in self.squares



@measure_time
def create_small(number_list):
    results = list()
    for number in number_list:
        checker = smallObject()
        res = checker.validate(number)
        results.append(res)
    return results


@measure_time
def create_big(number_list):
    results = list()
    for number in number_list:
        checker = bigObject()
        res = checker.validate(number)
        results.append(res)
    return results


if __name__ == "__main__":
    number_list = [2, 4, 8, 12, 25, 30, 50, 100, 200, 45, 345, 3453, 345345, 450]
    create_small(number_list)
    create_big(number_list)
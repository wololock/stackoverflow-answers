def x = [[a: 1, b: 2], [a: 1, b: 3], [a: 2, b: 4], [a: 3, b: 5]]
def y = [[f: 10, b: 2, g: 7], [f: 100, b: 3, g: 8], [f: 20, b: 4, g: 9], [f: 20, b: 6, g: 9]]
def expected = [[a: 1, b: 2, f: 10, g: 7], [a: 1, b: 3, f: 100, g: 8], [a: 2, b: 4, f: 20, g: 9], [a: 3, b: 5], [a: 3, b: 6, f: 20, g: 9]]

def shareSameKeyWithSameValue(Map<String, ?> a, Map<String, ?> b) {
    final Set<String> keysIntersectionFromEntries = (a.entrySet().intersect(b.entrySet())).key as Set
    final Set<String> keysIntersection = a.keySet().intersect(b.keySet())
    return !keysIntersectionFromEntries.isEmpty() && keysIntersectionFromEntries.containsAll(keysIntersection)
}

def result = [x, y].transpose().collect { a, b ->
    shareSameKeyWithSameValue(a, b) ? a + b : [a, a + b]
}.flatten()

assert result == expected

File myFile = new File("/tmp/numbers.txt")

def avg1 = myFile.readLines()
        .stream()
        .mapToInt({ s -> s.toInteger() })
        .average()
        .getAsDouble()

def lines = myFile.readLines()
def avg2 = lines.sum({ it.toInteger() }) / lines.size()

println "avg1 = ${avg1}"
println "avg2 = ${avg2}"


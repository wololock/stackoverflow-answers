
def foo(Map kwargs = [:], ... args) { "kwargs=${kwargs.toString()} args=${args.toString()}" }

def bar = { Map kwargs = [:], ... args -> "kwargs=${kwargs.toString()} args=${args.toString()}" }

def bar2 = { Map kwargs = [:], ... args = [] -> "kwargs=${kwargs.toString()} args=${args.toString()}" }


foo(1:1, 2:2)

foo(1:1, 2:2, 3, 4)

try {
    bar(1:1, 2:2, 3, 4)
} catch (MissingMethodException e) {}

try {
    bar(1:1, 2:2)
} catch (MissingMethodException e) {}

assert bar.class.methods.count { it.name == 'doCall'} == 2

bar2(1:1, 2:2)

bar2(1:1, 2:2, 3, 4)

assert bar2.class.methods.count { it.name == 'doCall'} == 3

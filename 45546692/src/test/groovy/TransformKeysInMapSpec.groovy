import spock.lang.Specification

import java.util.function.Function

class TransformKeysInMapSpec extends Specification {

    def 'should transform keys in Groovy map'() {
        given:
        final Function<String, String> identity = Function.identity()
        final Function<String, String> basicTransformation = { String key -> key.toUpperCase().reverse() }
        final Map<String, Function<String, String>> transformations = [abcd: basicTransformation, xyz: basicTransformation]
        final Map map= ["abcd":["name":"x", "age":"22"],"xyz":["name":"y", "age":"12"], "unchanged": ["name": "a", "age": "20"]]

        when:
        final Map newMap = map.collectEntries { [(transformations.getOrDefault(it.key, identity).apply(it.key)): it.value] }

        then:
        newMap == [DCBA:[name: 'x', age: '22'], ZYX:[name: 'y', age: '12'], unchanged:[name: 'a', age: '20']]
    }
}

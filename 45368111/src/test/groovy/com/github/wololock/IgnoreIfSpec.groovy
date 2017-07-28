package com.github.wololock

import spock.lang.IgnoreIf
import spock.lang.Specification

class IgnoreIfSpec extends Specification {

    static boolean skip = true

    @IgnoreIf({ IgnoreIfSpec.skip })
    def "should not execute this test if `IgnoreIfSepc.skip` is set to TRUE"() {
        when:
        def res = 1 + 1

        then:
        res == 2
    }

    def "should execute this test every time"() {
        when:
        def res = 1 + 1

        then:
        res == 2
    }
}

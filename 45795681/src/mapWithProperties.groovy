def junitResultList = [
        [
                name: "Testsuite A",
                children: [
                        failedTests: ["Test 1", "Test 2"],
                        skippedTests: []
                ]
        ],
        [
                name: "Testsuite B",
                children: [
                        failedTests: ["CursorTest"],
                        skippedTests: ["ClickTest", "DragNDropTest"]
                ]
        ]
]

println junitResultList.children.collect { it.failedTests + it.skippedTests }.flatten()

println junitResultList.children.collect { [it.failedTests, it.skippedTests] }.flatten()

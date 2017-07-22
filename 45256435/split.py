assert 'Braund, Mr. Owen Harris'.split(',') == ['Braund', ' Mr. Owen Harris']

assert 'Braund, Mr. Owen Harris'.split(',')[1] == ' Mr. Owen Harris'

assert 'Braund, Mr. Owen Harris'.split(',')[1].split('.') == [' Mr', ' Owen Harris']

assert 'Braund, Mr. Owen Harris'.split(',')[1].split('.')[0] == ' Mr'

assert 'Braund, Mr. Owen Harris'.split(',')[1].split('.')[0].strip() == 'Mr'

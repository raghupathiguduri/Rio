package opstring

import org.rioproject.config.Constants

def String getCodebase() {
    return System.getProperty(Constants.WEBSTER)
}

deployment(name:'Fixed Service Test') {

    codebase getCodebase()

    groups System.getProperty('org.rioproject.groups')

    service(name: 'Fixey McFixFix',
            fork: 'yes',
            type: 'fixed',
            jvmArgs: '-Xms8m -Xmx2000m -Dfoo=bar -Dssleep=5',
            environment:'LD_LIBRARY_PATH=$EG_HOME/native/blahblah') {

        interfaces {
            classes 'org.rioproject.test.simple.Fork'
            resources 'test-classes/'
        }
        implementation(class: 'org.rioproject.test.simple.ForkImpl') {
            resources 'test-classes/'
        }

        maintain 1

    }
}
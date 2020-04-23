package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return ok"
    request {
        method GET()
        url("/users") {
            headers {
                header("Authorization","Bearer FOO")
            }
        }
    }
    response {
        status 200
    }
}

//class testContract {
//}

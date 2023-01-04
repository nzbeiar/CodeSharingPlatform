package com.example.codesharingplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CodeSharingPlatformApplication {

    Code code = new Code(
            "public static void main(String[] args) {\n" +
                    "    SpringApplication.run(CodeSharingPlatform.class, args);\n" +
                    "}");


    @GetMapping("/code")
    public ResponseEntity<?> respondHTML() {
        System.out.println(1);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type",
                "text/html");
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body("<html>\n" +
                        "<head>\n" +
                        "    <title>Code</title>\n" +
                        "<style> " +
                        "span { color:green;}; #code_snippet { background: light-gray; border: 1px solid black; }"+
                        " </style>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<span id='load_date'>" + code.getDate() + "</span>\n" +
                        "    <pre id = 'code_snippet'>" + code.getCode() + "</pre>\n" +
                        "</body>\n" +
                        "</html>");
    }

    @GetMapping("/api/code")
    public ResponseEntity<?> respondCode() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type",
                "application/json");
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(code);
    }


    @PostMapping("/api/code/new")
    public ResponseEntity<?>  post(@RequestBody Code code) {
        this.code = code;
        return ResponseEntity.ok()
                .body("{}");
    }

    @GetMapping("/code/new")
    public ResponseEntity<?> createCode() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type",
                "text/html");
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body("""
                        <html>
                        <head>
                            <title>Create</title>
                        <script>
                        function send() {
                            let object = {
                                "code": document.getElementById("code_snippet").value
                            };
                            let json = JSON.stringify(object);
                            let xhr = new XMLHttpRequest();
                            xhr.open("POST", '/api/code/new', false)
                            xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
                            xhr.send(json);
                            if (xhr.status == 200) {
                              alert("Success!");
                            }
                        }
                        </script>
                        </head>
                        <body>
                        <textarea id='code_snippet' style= 'width: 461px; height: 249px;'> </textarea>
                        <br>
                        <button id = 'send_snippet' type='submit' onclick=send()> Submit </button>
                        </body>
                        </html>""");
    }

    public static void main(String[] args) {
        SpringApplication.run(CodeSharingPlatformApplication.class, args);
    }

}

<% try {

%>

<!DOCTYPE html>

<style>
    * {
        padding: 0;
        margin: 0;
        box-sizing: border-box;
    }

    @font-face {
        font-family: "Ubuntu";
        src: url(material/Ubuntu-Regular.ttf);
    }

    body {
        font-family: "Ubuntu";
        display: grid;
        place-items: center;
        height: 100vh;
        background: url(material/image/background.jpg) rgba(0,0,0,0.7);
        background-size: cover;
        background-repeat: no-repeat;
        background-blend-mode: darken;
    }

    h1 {
        font-weight: bold;
        margin: 0;
        letter-spacing: 2px;
    }

    p {
        font-size: 1em;
        font-weight: 100;
        line-height: 1.3em;
        letter-spacing: 2px;
        margin: 20px 0;
    }

    form {
        background-color: white;
        display: flex;
        align-items: center;
        justify-content: center;
        flex-direction: column;
        padding: 0 50px;
        height: 100%;
        text-align: center;
    }

    .sign-up-container input {
        border: none;
        border-bottom: 1px solid;
        font-size: 16px;
        padding: 10px 0;
        margin-top: 15px;
    }

    .sign-in-container input {
        border: none;
        border-bottom: 1px solid;
        font-size: 16px;
        padding: 10px 0;
        margin-top: 10px;
    }

    input:focus {
        outline: none;
    }

    input::placeholder {
        color: lightgray;
    }

    button {
        border-radius: 20px;
        border: 1px solid black;
        background: black;
        color: white;
        font-size: 15px;
        font-weight: bold;
        padding: 0.8em 3em;
        letter-spacing: 3px;
        text-transform: uppercase;
        transition: transform 80ms ease-in;
        cursor: pointer;
        margin-top: 20px;
    }

    button.ghost {
        background: transparent;
        border-radius: 0;
        border: 3px solid;
        color: black;
    }

    button.ghost:hover {
        background: black;
        color: white;
        border: 3px solid black;
        transition: 0.15s ease-in;
    }

    button:hover {
        opacity: 0.8;
    }

    span {
        font-size: 15px;
    }

    .sign-in-container h1:after {
        content: '';
        display: block;
        height: 4px;
        width: 100%;
        background-color: black;
        margin-top: 10px;
    }

    .sign-in-container a{
        font-size: 13px;
        text-decoration: underline;
        color: gray;
        margin-top: 5px;
    }

    .container {
        background-color: white;
        border-radius: 1em;
        box-shadow: 0 2px 12px rgba(0,0,0,0.4);
        position: relative;
        overflow: hidden;
        width: 750px;
        max-width: 100%;
        min-height: 600px;
    }

    .form-container {
        position: absolute;
        top: 0;
        height: 100%;
        transition: all 0.5s ease-out;
    }

    .sign-in-container {
        left: 0;
        width: 50%;
        z-index: 2;
    }

    .sign-up-container {
        left: 0;
        width: 50%;
        opacity: 0;
        z-index: 1;
    }

    .sign-up-container h1:after {
        content: '';
        display: block;
        height: 4px;
        width: 100%;
        background-color: black;
        margin-top: 10px;
    }

    .container.right-panel-active .sign-in-container {
        transform: translateX(100%);
    }

    .container.right-panel-active .sign-up-container {
        transform: translateX(100%);
        opacity: 1;
        z-index: 5;
        animation: show 0.5s;
    }

    @keyframe show {
        0%, 49.99% {
            opacity: 0;
            z-index: 1;
        }
        50%, 100% {
            opacity: 1;
            z-index: 5;
        }
    }

    .container.right-panel-active .overlay-container {
        transform: translateX(-100%);
    }

    .container.right-panel-active .overlay {
        transform: translateX(50%);
    }

    .container.right-panel-active .overlay-left {
        transform: translateX(0);
    }

    .container.right-panel-active .overlay-right {
        transform: translateX(20%);
    }

    .overlay-container {
        position: absolute;
        top: 0;
        left: 50%;
        width: 50%;
        height: 100%;
        overflow: hidden;
        transition: transform 0.5s ease-in-out;
        z-index: 100;
    }

    .overlay {
        background: rgb(50,50,50);
        background: linear-gradient(0deg, rgba(50,50,50,1) 0%, rgba(171,171,171,1) 36%, rgba(221,221,221,1) 91%);
        background-repeat: no-repeat;
        background-size: cover;
        background-position: 0, 0;
        position: relative;
        left: -100%;
        height: 100%;
        width: 200%;
        transform: translateX(0);
        transition: transform 0.5s ease-in-out;
    }

    .overlay-panel {
        position: absolute;
        display: flex;
        align-items: center;
        justify-content: center;
        flex-direction: column;
        padding: 0 40px;
        text-align: center;
        top: 0;
        height: 100%;
        width: 50%;
        transform: translateX(0);
        transition: transform 0.5s ease-in-out;
    }

    .overlay-left {
        transform: translateX(-20%);
    }

    .overlay-right {
        right: 0;
        transform: translateX(0)
    }

    .checkbox-container {
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .checkbox-text p{
        margin: 0;
        font-size: 13px;
    }

    .errorMessage {
        font-size: 13px;
        color: red;
        margin-top: 10px;
    }
    
    .error-message {
        font-size: 13px;
        color: red;
        margin-top: 10px;
    }
</style>

<html>
    <head>
        <title>Staff Log In</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" type="image/x-icon" href="material/image/small-logo.ico">
        <script src="https://kit.fontawesome.com/bfb46dc9da.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="container" id="container">
            <div class="form-container sign-in-container">
                <form action="ProcessStaffLogin" method="POST">
                    <h1>Sign in</h1>
                    <input type="text" name="staffusername" placeholder="Staff Username">
                    <div class="error-message">${errorMessage}</div>
                    <input type="password" name="staffpassword" placeholder="Password">
                    <button type="submit">Sign In</button>
                    <a href="#">Forgot Password?</a>
                </form>
            </div>
            <div class="overlay-container">
                <div class="overlay">
                    <div class="overlay-panel overlay-right">
                        <h1>Staff Login Here !</h1>
                        <p>Enter Staff Username to login.</p>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
<%} catch (Exception ex) {
        out.print("Something went wrong");
        out.println("<a href='staffLogin.jsp'>Click me back to login</a>");
    }%>


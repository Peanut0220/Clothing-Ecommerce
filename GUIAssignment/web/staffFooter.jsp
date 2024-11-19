<!DOCTYPE html>
<style>
    /*footer*/
    .footer-wrapper{
        display: flex;
        padding: 0 35px;
        padding-top: 20px;
        padding-bottom: 20px;
        border-bottom: 1px solid lightgray;
        border-top: 2px solid lightgray;
    }

    .footer-content {
        flex: 1;
        padding-right: 10px;
    }

    .title p.main{
        font-size: 23px;
        text-transform: uppercase;
        font-weight: lighter;
        letter-spacing: 2px;
        padding-bottom: 10px;
    }

    .title p.sub {
        font-weight: lighter;
        font-size: 17px;
        padding-top: 8px;
        padding-bottom: 10px;
        letter-spacing: 1px;
    }

    .link {
        padding-top: 10px;
    }

    .about-us ul li {
        list-style: none;
        padding-top: 15px;
    }

    .about-us a {
        font-size: 17px;
        text-decoration: none;
        color: black;
    }

    .about-us a:hover {
        text-decoration: underline;
        text-underline-offset: 5px;
    }

    .help ul li {
        list-style: none;
        padding-top: 15px;
    }

    .help ul li a {
        text-decoration: none;
        font-size: 18px;
        color: black;
    }

    .help ul li a:hover {
        text-decoration: underline;
        text-underline-offset: 5px;
    }

    .e-newsletter input {
        border: none;
        border-bottom: 1px solid;
        padding-bottom: 10px;
        padding-top: 10px;
        padding-right: 10px;
    }

    .e-newsletter input:focus {
        outline: none;
    }

    .e-newsletter input::placeholder {
        color: lightgray;
    }

    .e-newsletter button {
        padding: 10px;
        font-size: 15px;
        background-color: black;
        color: white;
        margin-left: 12px;
    }

    .e-newsletter button:hover {
        cursor: pointer;
        opacity: 0.8;
    }

    .social .social-icon {
        padding-top: 9px;
    }

    .social a {
        font-size: 32px;
        margin-right: 20px;
        color: black;
    }

    .social a:hover {
        opacity: 0.7;
    }

    .footer-wrapper-2 {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 20px 50px;
        background-color: rgb(246, 246, 246);
    }

    .footer-wrapper-2 span {
        font-size: 17px;
        text-transform: uppercase;
        color: gray;
    }

    .terms-privacy a {
        margin-left: 35px;
        color: gray;
    }
</style>
<html>
    <head>
        <title></title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <footer>
        <div class="footer-wrapper">
            <div class="footer-content">
                <div class="about-us">
                    <div class="title">
                        <p class="main">${companyName}</p>
                    </div>
                    <div class="link">
                        <ul>
                            <li><a href="aboutUs.jsp">About Us</a></li>
                            <li><a href="FAQ.jsp">FAQ</a></li>
                            <li><a href="mailto:${companyEmail}">${companyEmail}</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="footer-content">
                <div class="e-newsletter">
                    <div class="title">
                        <p class="main">E-Newsletter</p>
                        <p class="sub">Enter your email to receive the latest product</p>
                    </div>
                    <form action="_POST">
                        <input type="text" placeholder="example@example.com">
                        <button type="submit">Subscribe</button>
                    </form>
                </div>
            </div>
            <div class="footer-content">
                <div class="social">
                    <div class="title">
                        <p class="main">Follow Us</p>
                    </div>
                    <div class="social-icon">
                        <a href="url"><i class="fa-brands fa-facebook"></i></a>
                        <a href="url"><i class="fa-brands fa-whatsapp"></i></a>
                        <a href="https://www.instagram.com/guanw_0904/"><i class="fa-brands fa-instagram"></i></a>
                        <a href="https://www.youtube.com/channel/UCrr570-ww01qhtGPt3ARYsw"><i class="fa-brands fa-youtube"></i></a>
                    </div>
                </div>
            </div>
            <div class="footer-content">
                <div class="find-us">
                    <div class="title">
                        <p class="main">Find Us At</p>
                    </div>
                    <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d995.8846573273233!2d101.7283544563682!3d3.215045972001272!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31cc3843bfb6a031%3A0x2dc5e067aae3ab84!2sTunku%20Abdul%20Rahman%20University%20of%20Management%20and%20Technology%20(TAR%20UMT)!5e0!3m2!1sen!2smy!4v1678633719942!5m2!1sen!2smy" width="350" height="250" style="border:0;"></iframe>
                </div>
            </div>
        </div>
        <div class="footer-wrapper-2">
            <span>${copyright}</span>
            <div class="terms-privacy">
                <a href="">Terms of Use</a>
                <a href="url">Privacy Policy</a>
            </div>
        </div>
    </footer>
</html>


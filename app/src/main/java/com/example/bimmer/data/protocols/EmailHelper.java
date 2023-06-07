package com.example.bimmer.data.protocols;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailHelper {
    private static final String USERNAME = "klandrtp@yandex.ru";
    private static final String PASSWORD = "nlgzpxnopvigspsd";
    private static final String HOST = "smtp.yandex.ru";
    private static final String PORT = "465";

    public static void sendEmail(Context context, String name, String phone) {
        SendEmailTask task = new SendEmailTask(context);
        task.execute(name, phone);
    }

    private static class SendEmailTask extends AsyncTask<String, Void, Boolean> {
        private Context context;

        public SendEmailTask(Context context) {
            this.context = context.getApplicationContext();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            String name = params[0];
            String phone = params[1];

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", HOST);
            props.put("mail.smtp.port", PORT);
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        public PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(USERNAME, PASSWORD);
                        }
                    });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(USERNAME));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("klandrtp@ya.ru"));
                message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse("klandrtp@ya.ru"));
                message.setSubject("Новая запись");

                String emailText = "Имя: " + name + "\n" +
                        "Телефон: " + phone;
                message.setText(emailText);

                Transport.send(message);

                return true;
            } catch (MessagingException e) {
                e.printStackTrace();
                return false;
            }
        }

    }
}

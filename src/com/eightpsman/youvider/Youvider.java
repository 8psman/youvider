package com.eightpsman.youvider;

import java.io.*;
import java.net.*;

/**
 * Youvider
 * Created by 8psman on 1/23/2015.
 * Email: 8psman@gmail.com
 */
public class Youvider {

    public static String convertToString(InputStream inputStream){
        StringWriter writer = new StringWriter();
        char[] buffer = new char[1024];
        int bytes;
        try{
            InputStreamReader reader = new InputStreamReader(inputStream);
            while ((bytes = reader.read(buffer)) != -1){
                writer.write(buffer, 0, bytes);
            }
        }catch (IOException ex){
            return null;
        }
        return writer.toString();
    }

    public static void main(String args[]) throws IOException {

        // Get web content
        String webLink = "https://www.youtube.com/watch?v=9bZkp7q19f0";
        URL webUrl = new URL(webLink);
        HttpURLConnection webCon = (HttpURLConnection) webUrl.openConnection();

        InputStream webInput = webCon.getInputStream();

        String content = convertToString(webInput);

        YouviderParser.parse(content);

        if (true)
            return;

        String urlString = "https%3A%2F%2Fr1---sn-8qj-i5oy.googlevideo.com%2Fvideoplayback%3Fsparams%3Ddur%252Cid%252Cinitcwndbps%252Cip%252Cipbits%252Citag%252Cmime%252Cmm%252Cms%252Cmv%252Cpl%252Cratebypass%252Crequiressl%252Csource%252Cupn%252Cexpire%26fexp%3D900718%252C907263%252C927622%252C9405640%252C9405932%252C941004%252C943917%252C946008%252C947225%252C948124%252C952302%252C952605%252C952901%252C955301%252C957103%252C957105%252C957201%252C959701%26ip%3D14.162.147.25%26id%3Do-AK1FpaRiJ7HZZ9M-_VKTrmJ0UwVBhgugmFwNK_MJvhsr%26requiressl%3Dyes%26ratebypass%3Dyes%26source%3Dyoutube%26pl%3D19%26initcwndbps%3D678750%26dur%3D1225.572%26itag%3D22%26sver%3D3%26ipbits%3D0%26key%3Dyt5%26mime%3Dvideo%252Fmp4%26expire%3D1422030429%26upn%3DJwrhRtlK1p4%26mm%3D31%26ms%3Dau%26signature%3DE43C0838AE63CE11165D91EF1B235E9895F59702.55D216FF170C5568ED9585B8EB4C1E86CFADFA44%26mv%3Dm%26mt%3D1422008777";
//        String urlString = "\"adaptive_fmts\": \"itag=136\\u0026lmt=1417277341282624\\u0026clen=246221574\\u0026size=1280x720\\u0026bitrate=2226594\\u0026index=711-3466\\u0026projection_type=1\\u0026url=https%3A%2F%2Fr1---sn-8qj-i5oy.googlevideo.com%2Fvideoplayback%3Fsparams%3Dclen%252Cdur%252Cgir%252Cid%252Cinitcwndbps%252Cip%252Cipbits%252Citag%252Ckeepalive%252Clmt%252Cmime%252Cmm%252Cms%252Cmv%252Cpl%252Crequiressl%252Csource%252Cupn%252Cexpire%26fexp%3D900718%252C907263%252C927622%252C9405640%252C9405932%252C941004%252C943917%252C946008%252C947225%252C948124%252C952302%252C952605%252C952901%252C955301%252C957103%252C957105%252C957201%252C959701%26ip%3D14.162.147.25%26clen%3D246221574%26keepalive%3Dyes%26id%3Do-AK1FpaRiJ7HZZ9M-_VKTrmJ0UwVBhgugmFwNK_MJvhsr%26gir%3Dyes%26requiressl%3Dyes%26source%3Dyoutube%26pl%3D19%26initcwndbps%3D678750%26dur%3D1225.440%26itag%3D136%26sver%3D3%26lmt%3D1417277341282624%26ipbits%3D0%26key%3Dyt5%26mime%3Dvideo%252Fmp4%26expire%3D1422030429%26upn%3DJwrhRtlK1p4%26mm%3D31%26ms%3Dau%26signature%3DE44BA89DA3DA3599D092D0D114B287AC091E3D24.3C83D0876E9D20BC90C07F715835F1818D702C15%26mv%3Dm%26mt%3D1422008777\\u0026fps=25\\u0026init=0-710\\u0026type=video%2Fmp4%3B+codecs%3D%22avc1.4d401f%22,itag=135\\u0026lmt=1417269702905044\\u0026clen=110316733\\u0026size=854x480\\u0026bitrate=1119629\\u0026index=710-3465\\u0026projection_type=1\\u0026url=https%3A%2F%2Fr1---sn-8qj-i5oy.googlevideo.com%2Fvideoplayback%3Fsparams%3Dclen%252Cdur%252Cgir%252Cid%252Cinitcwndbps%252Cip%252Cipbits%252Citag%252Ckeepalive%252Clmt%252Cmime%252Cmm%252Cms%252Cmv%252Cpl%252Crequiressl%252Csource%252Cupn%252Cexpire%26fexp%3D900718%252C907263%252C927622%252C9405640%252C9405932%252C941004%252C943917%252C946008%252C947225%252C948124%252C952302%252C952605%252C952901%252C955301%252C957103%252C957105%252C957201%252C959701%26ip%3D14.162.147.25%26clen%3D110316733%26keepalive%3Dyes%26id%3Do-AK1FpaRiJ7HZZ9M-_VKTrmJ0UwVBhgugmFwNK_MJvhsr%26gir%3Dyes%26requiressl%3Dyes%26source%3Dyoutube%26pl%3D19%26initcwndbps%3D678750%26dur%3D1225.440%26itag%3D135%26sver%3D3%26lmt%3D1417269702905044%26ipbits%3D0%26key%3Dyt5%26mime%3Dvideo%252Fmp4%26expire%3D1422030429%26upn%3DJwrhRtlK1p4%26mm%3D31%26ms%3Dau%26signature%3D054C7652683836E09CA8F2815B0C08B4D3ACF8AF.EB3276BC7A987C320D581882B840B7CC78EC3A70%26mv%3Dm%26mt%3D1422008777\\u0026fps=25\\u0026init=0-709\\u0026type=video%2Fmp4%3B+codecs%3D%22avc1.4d401e%22,itag=134\\u0026lmt=1417269544107843\\u0026clen=57353344\\u0026size=640x360\\u0026bitrate=618096\\u0026index=710-3465\\u0026projection_type=1\\u0026url=https%3A%2F%2Fr1---sn-8qj-i5oy.googlevideo.com%2Fvideoplayback%3Fsparams%3Dclen%252Cdur%252Cgir%252Cid%252Cinitcwndbps%252Cip%252Cipbits%252Citag%252Ckeepalive%252Clmt%252Cmime%252Cmm%252Cms%252Cmv%252Cpl%252Crequiressl%252Csource%252Cupn%252Cexpire%26fexp%3D900718%252C907263%252C927622%252C9405640%252C9405932%252C941004%252C943917%252C946008%252C947225%252C948124%252C952302%252C952605%252C952901%252C955301%252C957103%252C957105%252C957201%252C959701%26ip%3D14.162.147.25%26clen%3D57353344%26keepalive%3Dyes%26id%3Do-AK1FpaRiJ7HZZ9M-_VKTrmJ0UwVBhgugmFwNK_MJvhsr%26gir%3Dyes%26requiressl%3Dyes%26source%3Dyoutube%26pl%3D19%26initcwndbps%3D678750%26dur%3D1225.440%26itag%3D134%26sver%3D3%26lmt%3D1417269544107843%26ipbits%3D0%26key%3Dyt5%26mime%3Dvideo%252Fmp4%26expire%3D1422030429%26upn%3DJwrhRtlK1p4%26mm%3D31%26ms%3Dau%26signature%3DD8A97B398802A86664BC85A1EBC4F62F6D7B4DB3.8677033270998FB47B06B82591AFEBA0E7CD326F%26mv%3Dm%26mt%3D1422008777\\u0026fps=25\\u0026init=0-709\\u0026type=video%2Fmp4%3B+codecs%3D%22avc1.4d401e%22,itag=133\\u0026lmt=1417269358662633\\u0026clen=37460269\\u0026size=426x240\\u0026bitrate=253909\\u0026index=675-3430\\u0026projection_type=1\\u0026url=https%3A%2F%2Fr1---sn-8qj-i5oy.googlevideo.com%2Fvideoplayback%3Fsparams%3Dclen%252Cdur%252Cgir%252Cid%252Cinitcwndbps%252Cip%252Cipbits%252Citag%252Ckeepalive%252Clmt%252Cmime%252Cmm%252Cms%252Cmv%252Cpl%252Crequiressl%252Csource%252Cupn%252Cexpire%26fexp%3D900718%252C907263%252C927622%252C9405640%252C9405932%252C941004%252C943917%252C946008%252C947225%252C948124%252C952302%252C952605%252C952901%252C955301%252C957103%252C957105%252C957201%252C959701%26ip%3D14.162.147.25%26clen%3D37460269%26keepalive%3Dyes%26id%3Do-AK1FpaRiJ7HZZ9M-_VKTrmJ0UwVBhgugmFwNK_MJvhsr%26gir%3Dyes%26requiressl%3Dyes%26source%3Dyoutube%26pl%3D19%26initcwndbps%3D678750%26dur%3D1225.440%26itag%3D133%26sver%3D3%26lmt%3D1417269358662633%26ipbits%3D0%26key%3Dyt5%26mime%3Dvideo%252Fmp4%26expire%3D1422030429%26upn%3DJwrhRtlK1p4%26mm%3D31%26ms%3Dau%26signature%3DA9EC629D38BD733BA6D35BE50807482240BF8554.5F7D170ACFFDE4476347E3BA2E7AE10E1932D2C8%26mv%3Dm%26mt%3D1422008777\\u0026fps=25\\u0026init=0-674\\u0026type=video%2Fmp4%3B+codecs%3D%22avc1.4d4015%22,itag=160\\u0026lmt=1417269311772216\\u0026clen=16826333\\u0026size=256x144\\u0026bitrate=114927\\u0026index=672-3427\\u0026projection_type=1\\u0026url=https%3A%2F%2Fr1---sn-8qj-i5oy.googlevideo.com%2Fvideoplayback%3Fsparams%3Dclen%252Cdur%252Cgir%252Cid%252Cinitcwndbps%252Cip%252Cipbits%252Citag%252Ckeepalive%252Clmt%252Cmime%252Cmm%252Cms%252Cmv%252Cpl%252Crequiressl%252Csource%252Cupn%252Cexpire%26fexp%3D900718%252C907263%252C927622%252C9405640%252C9405932%252C941004%252C943917%252C946008%252C947225%252C948124%252C952302%252C952605%252C952901%252C955301%252C957103%252C957105%252C957201%252C959701%26ip%3D14.162.147.25%26clen%3D16826333%26keepalive%3Dyes%26id%3Do-AK1FpaRiJ7HZZ9M-_VKTrmJ0UwVBhgugmFwNK_MJvhsr%26gir%3Dyes%26requiressl%3Dyes%26source%3Dyoutube%26pl%3D19%26initcwndbps%3D678750%26dur%3D1225.520%26itag%3D160%26sver%3D3%26lmt%3D1417269311772216%26ipbits%3D0%26key%3Dyt5%26mime%3Dvideo%252Fmp4%26expire%3D1422030429%26upn%3DJwrhRtlK1p4%26mm%3D31%26ms%3Dau%26signature%3DFA007B93A2ACF1BE2DC542C626A6968A4F2EAD4C.9579E234967736C16AE76457396D71711131C5D6%26mv%3Dm%26mt%3D1422008777\\u0026fps=13\\u0026init=0-671\\u0026type=video%2Fmp4%3B+codecs%3D%22avc1.4d400c%22,itag=140\\u0026clen=19675847\\u0026init=0-591\\u0026index=592-2099\\u0026lmt=1417269175348947\\u0026bitrate=130179\\u0026projection_type=1\\u0026type=audio%2Fmp4%3B+codecs%3D%22mp4a.40.2%22\\u0026url=https%3A%2F%2Fr1---sn-8qj-i5oy.googlevideo.com%2Fvideoplayback%3Fsparams%3Dclen%252Cdur%252Cgir%252Cid%252Cinitcwndbps%252Cip%252Cipbits%252Citag%252Ckeepalive%252Clmt%252Cmime%252Cmm%252Cms%252Cmv%252Cpl%252Crequiressl%252Csource%252Cupn%252Cexpire%26fexp%3D900718%252C907263%252C927622%252C9405640%252C9405932%252C941004%252C943917%252C946008%252C947225%252C948124%252C952302%252C952605%252C952901%252C955301%252C957103%252C957105%252C957201%252C959701%26ip%3D14.162.147.25%26clen%3D19675847%26keepalive%3Dyes%26id%3Do-AK1FpaRiJ7HZZ9M-_VKTrmJ0UwVBhgugmFwNK_MJvhsr%26gir%3Dyes%26requiressl%3Dyes%26source%3Dyoutube%26pl%3D19%26initcwndbps%3D678750%26dur%3D1225.572%26itag%3D140%26sver%3D3%26lmt%3D1417269175348947%26ipbits%3D0%26key%3Dyt5%26mime%3Daudio%252Fmp4%26expire%3D1422030429%26upn%3DJwrhRtlK1p4%26mm%3D31%26ms%3Dau%26signature%3D354E020E5416EAC0004CDBF2B9D6B2324488FBE1.B16B2AC575AA0411138334503642E82D6EA741CF%26mv%3Dm%26mt%3D1422008777\", ";
//
//        String encoder = "UTF-8";
//        try {
//            urlString = URLDecoder.decode(urlString, encoder);
//            urlString = urlString.replace("\\u0026", "\n");
//            System.out.println(urlString);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//        if (true) return;

        InputStream input = null;
        OutputStream output = null;
        HttpURLConnection connection = null;
        URL url = null;
        try {
            url = new URL(urlString);
            connection = (HttpURLConnection )url.openConnection();
            connection.connect();

//            connection.setReadTimeout(20000);

            File file = new File("E:/test.mp4");


            /** video size length */
            int fileLength = connection.getContentLength();

            System.out.println(fileLength);
            System.out.println(fileLength / 1024f / 1024f);

            /** download video */
            input = new BufferedInputStream(connection.getInputStream());
            output = new FileOutputStream(file);

            byte buffered[] = new byte[1024];
            long downloaded = 0;
            int count;
            int progress = 0;

            while (true) {
                count = input.read(buffered);
                if (count < 0) break;
                System.out.println("Read: " + count);
                downloaded += count;
                output.write(buffered, 0, count);

                if (fileLength > 0){
                    int newProgress = (int)(downloaded * 100f / fileLength);
                    if (newProgress > progress){
                        progress = newProgress;
//                        updateProgress(progress + " %");
                    }
                }else{
//                    updateProgress((int)(downloaded / 1024f / 1024f) + " MB");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            try {
                if (output != null)
                    output.flush();
                if (output != null)
                    output.close();
                if (input!= null)
                    input.close();
                if (connection != null)
                    connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

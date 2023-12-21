package com.testwebpage.speech;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.DefaultFFMPEGLocator;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.FFMPEGLocator;
import it.sauronsoftware.jave.InputFormatException;

//import it.sauronsoftware.jave.FFMPEGLocator;

@Service
public class FileService {
	
	public class CustomFFMPEGLocator extends FFMPEGLocator {
	    @Override
	    protected String getFFMPEGExecutablePath() {
	        return "/Users/shinjongho/Desktop/ffmpeg"; // FFmpeg 실행 파일의 경로 설정
	    }
	}
	
	public String makeFlacFile ( MultipartFile mp4File) throws IOException {
		String result = "";
		
		String uuid = UUID.randomUUID().toString().replace("-", "");
		
		try {
//			FFMPEGLocator locator = new FFMPEGLocator() {
//	            @Override
//	            protected String getFFMPEGExecutablePath() {
//	                // FFmpeg 실행 파일의 경로를 반환
//	                // 예: "/usr/local/bin/ffmpeg" 또는 "C:\\ffmpeg\\bin\\ffmpeg.exe"
//	                return "/Users/shinjongho/Desktop/ffmpeg";
//	            }
//	        };
			
			System.out.println(mp4File.getOriginalFilename());
			String filePath = "/Users/shinjongho/";
			
	
			
			File source = new File("/Users/shinjongho/Desktop/"+mp4File.getOriginalFilename());
			mp4File.transferTo(source);
			
			File target = new File(filePath + uuid + ".wav" );
			
            // 오디오 포맷 속성 정의. Google speech to text api 동작 조건
			AudioAttributes audio = new AudioAttributes();
			audio.setBitRate(new Integer(16000)); // 샘플 레이트
			audio.setChannels(new Integer(1)); // Mono 채널로 설정해야 speech to text api 사용 가능
//			audio.setSamplingRate((Integer)44100);
			audio.setCodec("pcm_s16le");  // 코덱 조건
			
			EncodingAttributes attrs = new EncodingAttributes();
			attrs.setFormat("wav"); // 포맷 설정
			attrs.setAudioAttributes(audio);
			
			System.out.println(source);
			System.out.println(target);
			System.out.println(attrs);
			
			Encoder encoder = new Encoder(new CustomFFMPEGLocator());
			encoder.encode(source, target, attrs, null);
			
			result = target.getPath();

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InputFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncoderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
		System.out.println("완료");
		return result;
	}
}

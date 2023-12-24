package com.testwebpage.speech;

import java.io.File;
//import java.io.IOException;
//import java.util.UUID;

import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

//import it.sauronsoftware.jave.AudioAttributes;
//import it.sauronsoftware.jave.DefaultFFMPEGLocator;
//import it.sauronsoftware.jave.Encoder;
//import it.sauronsoftware.jave.EncoderException;
//import it.sauronsoftware.jave.EncodingAttributes;
//import it.sauronsoftware.jave.FFMPEGLocator;
//import it.sauronsoftware.jave.InputFormatException;
//import it.sauronsoftware.jave.VideoAttributes;

import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avformat;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.FFmpegLogCallback;
import org.bytedeco.javacv.Frame;

//import it.sauronsoftware.jave.FFMPEGLocator;

@Service
public class FileService {
	
	public void convertVideoToAudio(MultipartFile videoFile) {
        try {
        	FFmpegLogCallback.set();
        	
            // 임시 파일 생성
            File tempVideoFile = File.createTempFile("inputVideo", ".mp4");
            File tempAudioFile = File.createTempFile("outputAudio", ".mp3");

            // MultipartFile을 File로 변환
            videoFile.transferTo(tempVideoFile);

            // FFmpeg 초기화
            avutil.av_log_set_level(avutil.AV_LOG_ERROR);
            avformat.av_register_all();
            avcodec.avcodec_register_all();

            // FFmpeg 실행
            FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(tempVideoFile);
            grabber.start();

            FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(tempAudioFile, grabber.getImageWidth(), grabber.getImageHeight(), 1);
            recorder.setAudioChannels(1);
            recorder.setSampleRate(44100);
            recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264); 
            recorder.setAudioCodec(avcodec.AV_CODEC_ID_MP3);
            recorder.setFormat("mp3");
            recorder.start();

            Frame frame;
            while ((frame = grabber.grab()) != null) {
                recorder.record(frame);
            }

            recorder.stop();
            grabber.stop();
            
            String outputFileName = "convertedAudio_" + System.currentTimeMillis() + ".mp3";
            File outputFile = new File("/Users/shinjongho/Desktop/", outputFileName);
            tempAudioFile.renameTo(outputFile);

            // 여기에서 tempAudioFile을 사용하면 됩니다.

        } catch (Exception e) {
            e.printStackTrace();
            // 예외 처리
        }
	}
	
	
//	public class CustomFFMPEGLocator extends FFMPEGLocator {
//	    @Override
//	    protected String getFFMPEGExecutablePath() {
//	        return "/Users/shinjongho/Desktop/ffmpeg"; // FFmpeg 실행 파일의 경로 설정
//	    }
//	}
//	
//	public String makeFlacFile ( MultipartFile mp4File) throws IOException {
//		String result = "";
//		
//		String uuid = UUID.randomUUID().toString().replace("-", "");
//		
//		try {
////			FFMPEGLocator locator = new FFMPEGLocator() {
////	            @Override
////	            protected String getFFMPEGExecutablePath() {
////	                // FFmpeg 실행 파일의 경로를 반환
////	                // 예: "/usr/local/bin/ffmpeg" 또는 "C:\\ffmpeg\\bin\\ffmpeg.exe"
////	                return "/Users/shinjongho/Desktop/ffmpeg";
////	            }
////	        };
//			
//			System.out.println(mp4File.getOriginalFilename());
//			String filePath = "/Users/shinjongho/";
//			
//	
//			
//			File source = new File(mp4File.getOriginalFilename());
//			mp4File.transferTo(source);
//			
//			File target = new File(filePath + uuid + ".wav" );
//			
//            // 오디오 포맷 속성 정의. Google speech to text api 동작 조건
//			AudioAttributes audio = new AudioAttributes();
//			audio.setBitRate(new Integer(16000)); // 샘플 레이트
//			audio.setChannels(new Integer(1)); // Mono 채널로 설정해야 speech to text api 사용 가능
////			audio.setSamplingRate((Integer)44100);
//			audio.setCodec("pcm_s16le");  // 코덱 조건
//			
//			EncodingAttributes attrs = new EncodingAttributes();
//			attrs.setFormat("wav"); // 포맷 설정
//			attrs.setAudioAttributes(audio);
//			
//			System.out.println(source);
//			System.out.println(target);
//			System.out.println(attrs);
//			
//			Encoder encoder = new Encoder(new CustomFFMPEGLocator());
//			encoder.encode(source, target, attrs, null);
//			
//			result = target.getPath();
//
//		} catch (IllegalArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InputFormatException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (EncoderException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(result);
//		System.out.println("완료");
//		return result;
//	}
	
//	public void convertVideoToAudio(MultipartFile mp4File, String outputAudioPath) {
//        try {
//        	String inputVideoPath = "/Users/shinjongho/Desktop/"+mp4File.getOriginalFilename();
//        	
//        	System.out.println(inputVideoPath);
//        	
//        	Encoder encoder = new Encoder();
//            
//            // 오디오 속성 설정
//            AudioAttributes audioAttributes = new AudioAttributes();
//            audioAttributes.setCodec("libmp3lame");
//            audioAttributes.setBitRate(new Integer(128000)); // 비트레이트 설정
//            
//            // 비디오 속성 설정 (비디오를 추출하는 것이 아니므로 비디오 속성은 필요하지 않을 수 있음)
//            VideoAttributes videoAttributes = new VideoAttributes();
//            
//            EncodingAttributes attributes = new EncodingAttributes();
//            attributes.setFormat("mp3");
//            attributes.setAudioAttributes(audioAttributes);
//            attributes.setVideoAttributes(videoAttributes);
//
//            encoder.encode(new File(inputVideoPath), new File(outputAudioPath), attributes);
//
//            System.out.println("Audio extraction complete.");
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.err.println("Error extracting audio: " + e.getMessage());
//        }
//    }
}

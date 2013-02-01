/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class oracle_cubist_jni_JniCubistOracle */

#ifndef _Included_oracle_cubist_jni_JniCubistOracle
#define _Included_oracle_cubist_jni_JniCubistOracle
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     oracle_cubist_jni_JniCubistOracle
 * Method:    initiateCubist
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_oracle_cubist_jni_JniCubistOracle_initiateCubist
  (JNIEnv *, jobject, jstring);

/*
 * Class:     oracle_cubist_jni_JniCubistOracle
 * Method:    getPrediction
 * Signature: (Ljava/lang/String;)D
 */
JNIEXPORT jdouble JNICALL Java_oracle_cubist_jni_JniCubistOracle_getPrediction
  (JNIEnv *, jobject, jstring);

/*
 * Class:     oracle_cubist_jni_JniCubistOracle
 * Method:    getPredictionAndError
 * Signature: (Ljava/lang/String;)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_oracle_cubist_jni_JniCubistOracle_getPredictionAndError
  (JNIEnv *, jobject, jstring);

#ifdef __cplusplus
}
#endif
#endif

/*
 * art.h
 *
 *  Created on: 2014��2��23��
 *      Author: Administrator
 */

#ifndef ART_H_
#define ART_H_

namespace art {
namespace mirror {
  class ArtMethod;
}  // namespace mirror
}

bool HookArtMethod(JNIEnv *jenv,jmethodID jmethod);
void Abort_();

#endif /* ART_H_ */

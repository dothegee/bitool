# 1. 🧩 `bitool_java`(백엔드) + `bitool_java_front`(프론트엔드) → `bitool`로 병합

---

# 🎯 병합 목표

| 이름                  | 설명                                 |
| ------------------- | ---------------------------------- |
| `bitool_java`       | 백엔드 저장소 (`bitool_back/` 폴더로 병합)    |
| `bitool_java_front` | 프론트엔드 저장소 (`bitool_front/` 폴더로 병합) |
| `bitool`            | 병합 대상 메인 저장소             |

---
## ✅ 1단계: 메인 저장소 준비
```bash
# 1-1 메인 저장소 remote
git init https://github.com/dothegee/bitool.git
git remote add origin 

```


```bash
# 1-2. 메인 저장소 clone
git clone https://github.com/dothegee/bitool.git
cd bitool
```

---

## ✅ 2단계: 백엔드 저장소 병합 (`bitool_back`)

```bash
# 2. 백엔드 저장소를 remote로 추가
git remote add backend-repo https://github.com/dothegee/bitool_java.git
git fetch backend-repo

# 3. 백엔드 커밋을 bitool_back/ 하위 폴더로 병합 (히스토리 포함)
git read-tree --prefix=backend/ -u backend-repo/main

# 4. 커밋 기록 반영
git commit -m "backend merge"
```

---

### ✅ 3단계: 프론트 저장소 병합 (`bitool_front`)

```bash
# 1. 프론트 저장소를 remote로 추가
git remote add frontend-repo https://github.com/dothegee/bitool_java_front.git
git fetch frontend-repo

# 2. 프론트 커밋을 bitool_front/ 하위 폴더로 병합
git read-tree --prefix=frontend/ -u frontend-repo/main

# 3. 커밋 기록 반영
git commit -m "frontend merge"
```

---

### ✅ 4단계: remote 정리

```bash
git remote remove backend-repo
git remote remove frontend-repo
```

---

### ✅ 5단계: 최종 푸시

```bash
git push origin main
```

---

## 📁 디렉토리 구조

```
bitool/
🔹 bitool_back/        # 기존 백어드 소스
    └── src/
    └── pom.xml
🔹 bitool_front/       # 기존 프론트 소스
    └── ...
🔹 .git/
🔹 .gitignore
🔹 README.md
```

> `git log`로 보면 **각 저장소의 커미트 히스토리가 복잡되어 있음**

---

## ✅ 헌정 요약

| 작업                       | 설명                              |
| ------------------------ | ------------------------------- |
| `git read-tree`          | 다른 저장소를 하위 폴더로 가져오며 commit 히스토리 유지 |
| `git remote add / fetch` | 외부 저장소 연결 및 commit 이력 가져오기         |
| `--prefix=...` 옵션        | 저장할 대상 디렉토리 지정                 |
| `git commit`             | 결과를 현재 레포지토리에 반영             |
| `git remote remove`      | 원격 remote 정리       |

---

# 2. 도커
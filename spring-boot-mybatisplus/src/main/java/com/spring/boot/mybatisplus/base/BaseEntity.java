/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.spring.boot.mybatisplus.base;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * description: Base Entity
 *
 * @author rock
 * @date 2020/5/12 0012 11:48
 */
@Data
public class BaseEntity implements Serializable {
    /**
     * 创建人ID
     */
    private String creatorId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人ID
     */
    private String updaterId;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
